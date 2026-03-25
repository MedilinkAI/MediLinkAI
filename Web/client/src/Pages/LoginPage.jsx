import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, Link } from 'react-router-dom';
import { loginStart, loginSuccess, loginFailure, clearError, loginStopLoading } from '../store/slices/authSlice';
import authService from '../services/authService';
import { Button } from '../components/ui/Button';
import { InputField } from '../components/ui/InputField';
import { FormWrapper } from '../components/ui/FormWrapper';

export const LoginPage = () => {
  const [step, setStep] = useState('login'); // 'login' | 'mfa'
  const [mfaType, setMfaType] = useState('otp'); // 'otp' | 'secret'
  const [mfaCode, setMfaCode] = useState('');

  const [identifier, setIdentifier] = useState('');
  const [password, setPassword] = useState('');
  const [formError, setFormError] = useState('');
  
  // Hold partial login response
  const [tempUser, setTempUser] = useState(null);
  
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { loading, error } = useSelector((state) => state.auth);

  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    setFormError('');
    dispatch(clearError());

    if (!identifier || !password) {
      return setFormError('Please fill in all fields');
    }

    try {
      dispatch(loginStart());
      const data = await authService.login(identifier, password);
      
      if (data.mfaRequired) {
        setTempUser(data);
        setStep('mfa');
        dispatch(clearError()); // Clear loading state visually but don't login yet
        dispatch(loginStopLoading());
      } else {
        dispatch(loginSuccess({ user: data, token: 'session_active' }));
        navigate('/dashboard');
      }
    } catch (err) {
      if (err.response && err.response.status === 401) {
          dispatch(loginFailure('Invalid email or password.'));
      } else {
          dispatch(loginFailure(err.response?.data?.message || err.message || 'Login Failed'));
      }
    }
  };

  const handleMfaSubmit = async (e) => {
    e.preventDefault();
    setFormError('');
    if (!mfaCode) return setFormError('Please enter your code.');
    
    try {
      dispatch(loginStart());
      if (mfaType === 'otp') {
        await authService.verifyOtp(identifier, mfaCode);
      } else {
        await authService.verifyMfaSecret(identifier, mfaCode);
      }
      
      // Verified successfully
      dispatch(loginSuccess({ user: tempUser, token: 'session_active' }));
      navigate('/dashboard');
    } catch (err) {
      dispatch(loginFailure(err.response?.data?.message || err.message || 'MFA Verification Failed'));
    }
  };

  if (step === 'mfa') {
    return (
      <div className="min-h-[calc(100vh-64px)] bg-slate-50 flex items-center justify-center p-4">
        <FormWrapper 
          title="Two-Factor Authentication" 
          description={mfaType === 'otp' ? "Enter the 6-digit code sent to your email." : "Enter your backup Secret Key."}
          onSubmit={handleMfaSubmit}
        >
          <div className="flex bg-slate-100 p-1 rounded-lg mb-6">
            <button 
              type="button" 
              className={`flex-1 py-1.5 text-sm font-medium rounded-md transition-shadow ${mfaType === 'otp' ? 'bg-white shadow text-primary-700' : 'text-gray-500 hover:text-gray-700'}`}
              onClick={() => { setMfaType('otp'); setMfaCode(''); setFormError(''); dispatch(clearError()); }}
            >
              Email OTP
            </button>
            <button 
              type="button" 
              className={`flex-1 py-1.5 text-sm font-medium rounded-md transition-shadow ${mfaType === 'secret' ? 'bg-white shadow text-primary-700' : 'text-gray-500 hover:text-gray-700'}`}
              onClick={() => { setMfaType('secret'); setMfaCode(''); setFormError(''); dispatch(clearError()); }}
            >
              Backup Secret
            </button>
          </div>

          <div className="space-y-4">
            <InputField
              label={mfaType === 'otp' ? "6-Digit OTP" : "Secret Key"}
              type="text"
              placeholder={mfaType === 'otp' ? "123456" : "Enter your secret key"}
              value={mfaCode}
              onChange={(e) => setMfaCode(e.target.value)}
              disabled={loading}
              maxLength={mfaType === 'otp' ? 6 : undefined}
            />
          </div>

          {(formError || error) && (
            <div className="p-3 bg-red-50 text-red-600 text-sm rounded-md border border-red-100 mt-4">
              {formError || error}
            </div>
          )}

          <Button type="submit" className="w-full mt-6" isLoading={loading}>
            Verify & Proceed
          </Button>
          
          <div className="mt-4 text-center">
            <button type="button" onClick={() => setStep('login')} className="text-sm text-gray-500 hover:text-gray-800">
               Cancel and return to login
            </button>
          </div>
        </FormWrapper>
      </div>
    );
  }

  return (
    <div className="min-h-[calc(100vh-64px)] bg-slate-50 flex items-center justify-center p-4">
      <FormWrapper 
        title="Welcome Back" 
        description="Enter your credentials to access your account"
        onSubmit={handleLoginSubmit}
      >
        <div className="space-y-4">
          <InputField
            label="Email or Mobile Number"
            type="text"
            placeholder="john@example.com"
            value={identifier}
            onChange={(e) => setIdentifier(e.target.value)}
            disabled={loading}
          />
          <InputField
            label="Password"
            type="password"
            placeholder="••••••••"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            disabled={loading}
          />
        </div>

        {(formError || error) && (
          <div className="p-3 bg-red-50 text-red-600 text-sm rounded-md border border-red-100 mt-4">
            {formError || error}
          </div>
        )}

        <Button type="submit" className="w-full mt-6" isLoading={loading}>
          Sign In
        </Button>

        <p className="text-center text-sm text-muted-foreground mt-6 text-gray-500">
          Don't have an account?{' '}
          <Link to="/register" className="text-primary-600 hover:text-primary-700 font-medium transition-colors">
            Register here
          </Link>
        </p>
      </FormWrapper>
    </div>
  );
};
