import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { loginSuccess } from '../store/slices/authSlice';
import authService from '../services/authService';
import { Button } from '../components/ui/Button';
import { InputField } from '../components/ui/InputField';
import { FormWrapper } from '../components/ui/FormWrapper';

export const RegisterPage = () => {
  const [step, setStep] = useState('form'); // 'form' | 'otp'
  const [mfaSecret, setMfaSecret] = useState(null);
  const [otp, setOtp] = useState('');

  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    confirmPassword: '',
    phoneNumber: '',
    mfaRequired: false
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleChange = (e) => {
    const value = e.target.type === 'checkbox' ? e.target.checked : e.target.value;
    setFormData({ ...formData, [e.target.name]: value });
  };

  const handleRegisterSubmit = async (e) => {
    e.preventDefault();
    setError('');

    if (!formData.name || !formData.email || !formData.password || !formData.phoneNumber) {
      return setError('Please fill in all required fields');
    }

    if (formData.password !== formData.confirmPassword) {
      return setError('Passwords do not match');
    }

    try {
      setLoading(true);
      const response = await authService.register(formData);
      if (response && response.mfaSecret) {
         setMfaSecret(response.mfaSecret);
      }
      setStep('otp');
    } catch (err) {
      setError(err.response?.data?.message || err.message || 'Registration failed');
    } finally {
      setLoading(false);
    }
  };

  const handleOtpSubmit = async (e) => {
    e.preventDefault();
    setError('');
    
    if (!otp || otp.length < 6) {
      return setError('Please enter a valid 6-digit OTP');
    }

    try {
      setLoading(true);
      await authService.verifyOtp(formData.email, otp);
      dispatch(loginSuccess({ 
        user: { name: formData.name, email: formData.email, phoneNumber: formData.phoneNumber }, 
        token: 'session_active' 
      }));
      navigate('/dashboard');
    } catch (err) {
      setError(err.response?.data?.message || err.message || 'OTP verification failed');
    } finally {
      setLoading(false);
    }
  };

  if (step === 'otp') {
    return (
      <div className="min-h-[calc(100vh-64px)] bg-slate-50 flex items-center justify-center p-4">
        <FormWrapper 
          title="Verify Email" 
          description={`We've sent a 6-digit OTP to ${formData.email}`}
          onSubmit={handleOtpSubmit}
          className="max-w-md"
        >
          {mfaSecret && (
             <div className="p-4 mb-4 bg-yellow-50 text-yellow-800 border border-yellow-200 rounded-md text-sm">
                <strong className="block mb-1 text-base">IMPORTANT:</strong> 
                You enabled MFA. Please save this backup Secret Key securely. If you cannot receive OTPs during login, this key is the only way into your account:
                <div className="font-mono text-xl mt-3 mb-1 font-bold break-all bg-white p-3 rounded text-center shadow-sm">
                  {mfaSecret}
                </div>
             </div>
          )}
          <div className="space-y-4">
            <InputField
              label="Enter OTP"
              type="text"
              placeholder="123456"
              value={otp}
              onChange={(e) => setOtp(e.target.value)}
              disabled={loading}
              maxLength={6}
            />
          </div>

          {error && (
            <div className="p-3 bg-red-50 text-red-600 text-sm rounded-md border border-red-100">
              {error}
            </div>
          )}

          <Button type="submit" className="w-full mt-6" isLoading={loading}>
            Verify Account
          </Button>
        </FormWrapper>
      </div>
    );
  }

  return (
    <div className="min-h-[calc(100vh-64px)] bg-slate-50 flex items-center justify-center p-4 pt-12 pb-24">
      <FormWrapper
        title="Create an Account"
        description="Join MediLink AI to manage your healthcare journey."
        onSubmit={handleRegisterSubmit}
        className="max-w-lg"
      >
        <div className="space-y-4">
          <InputField label="Full Name" name="name" placeholder="John Doe" value={formData.name} onChange={handleChange} disabled={loading} />
          <InputField label="Phone Number" type="text" name="phoneNumber" placeholder="1234567890" value={formData.phoneNumber} onChange={handleChange} disabled={loading} />
          <InputField label="Email Address" type="email" name="email" placeholder="john@example.com" value={formData.email} onChange={handleChange} disabled={loading} />
          
          <div className="flex items-center space-x-2 py-2">
            <input type="checkbox" id="mfaRequired" name="mfaRequired" checked={formData.mfaRequired} onChange={handleChange} disabled={loading} className="w-4 h-4 text-primary-600 rounded" />
            <label htmlFor="mfaRequired" className="text-sm font-medium text-foreground cursor-pointer">
              Enable Multi-Factor Authentication (MFA)
            </label>
          </div>

          <InputField label="Password" type="password" name="password" placeholder="••••••••" value={formData.password} onChange={handleChange} disabled={loading} />
          <InputField label="Confirm Password" type="password" name="confirmPassword" placeholder="••••••••" value={formData.confirmPassword} onChange={handleChange} disabled={loading} />
        </div>

        {error && <div className="p-3 bg-red-50 text-red-600 text-sm rounded-md border border-red-100">{error}</div>}
        <Button type="submit" className="w-full mt-6" isLoading={loading}>Register & Verify</Button>
        <p className="text-center text-sm text-muted-foreground mt-6 text-gray-500">
          Already have an account? <Link to="/login" className="text-primary-600 hover:text-primary-700 font-medium transition-colors">Sign In</Link>
        </p>
      </FormWrapper>
    </div>
  );
};
