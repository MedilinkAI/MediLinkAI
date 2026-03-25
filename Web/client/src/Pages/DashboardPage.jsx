import { useSelector } from 'react-redux';
import { Activity, Shield, Users, FileText, Calendar, Plus } from 'lucide-react';
import { Button } from '../components/ui/Button';

export const DashboardPage = () => {
  const { user } = useSelector((state) => state.auth);

  const stats = [
    { label: 'Upcoming Appointments', value: '2', icon: <Calendar className="w-6 h-6 text-primary-600" /> },
    { label: 'Recent Reports', value: '5', icon: <FileText className="w-6 h-6 text-secondary-600" /> },
    { label: 'Active Prescriptions', value: '3', icon: <Activity className="w-6 h-6 text-orange-500" /> },
    { label: 'Family Members', value: '4', icon: <Users className="w-6 h-6 text-blue-500" /> },
  ];

  return (
    <div className="min-h-[calc(100vh-64px)] bg-slate-50 p-6 md:p-10">
      <div className="max-w-7xl mx-auto space-y-8">
        
        {/* Welcome Section */}
        <div className="flex flex-col md:flex-row justify-between items-start md:items-center bg-white p-8 rounded-2xl shadow-sm border border-border">
          <div>
            <h1 className="text-3xl font-bold text-foreground">Welcome back, {user?.name || 'User'}!</h1>
            <p className="text-gray-500 mt-2">Here's the latest update on your health profile.</p>
          </div>
          <Button className="mt-4 md:mt-0 shadow-md">
            <Plus className="w-4 h-4 mr-2" />
            Book Consult
          </Button>
        </div>

        {/* Stats Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          {stats.map((stat, i) => (
            <div key={i} className="bg-white p-6 rounded-xl border border-border shadow-soft flex items-center space-x-4">
              <div className="p-3 bg-slate-50 rounded-lg shrink-0">
                {stat.icon}
              </div>
              <div>
                <p className="text-sm font-medium text-gray-500">{stat.label}</p>
                <p className="text-2xl font-bold text-foreground mt-1">{stat.value}</p>
              </div>
            </div>
          ))}
        </div>

        {/* Content Section */}
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
          <div className="bg-white p-6 rounded-xl border border-border lg:col-span-2 shadow-soft">
            <div className="flex justify-between items-center mb-6">
              <h3 className="text-xl font-bold text-foreground">Recent Medical Records</h3>
              <Button variant="ghost" size="sm">View All</Button>
            </div>
            
            <div className="space-y-4">
              {/* Dummy records */}
              {[1, 2, 3].map((_, i) => (
                <div key={i} className="p-4 border border-border rounded-lg flex items-center justify-between hover:bg-slate-50 transition-colors cursor-pointer">
                  <div className="flex items-center space-x-4">
                    <div className="w-10 h-10 bg-primary-50 rounded-full flex items-center justify-center">
                      <FileText className="w-5 h-5 text-primary-600" />
                    </div>
                    <div>
                      <h4 className="font-semibold text-foreground">Blood Test Report</h4>
                      <p className="text-sm text-gray-500">Dr. Smith • Oct 12, 2023</p>
                    </div>
                  </div>
                  <Button variant="outline" size="sm">View</Button>
                </div>
              ))}
            </div>
          </div>

          <div className="bg-white p-6 rounded-xl border border-border shadow-soft self-start">
            <h3 className="text-xl font-bold text-foreground mb-6">Security & Auth</h3>
            <div className="flex items-start space-x-4">
              <div className="p-2 bg-green-50 rounded-full shrink-0">
                <Shield className="w-5 h-5 text-green-600" />
              </div>
              <div>
                <h4 className="font-semibold text-foreground">Account Secured</h4>
                <p className="text-sm text-gray-500 mt-1">Multi-factor authentication is active on your account.</p>
                <Button variant="outline" size="sm" className="mt-4 w-full">Manage Security</Button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
