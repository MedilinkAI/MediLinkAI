import { Link } from 'react-router-dom';
import { Shield, Activity, Clock, FileText, ArrowRight } from 'lucide-react';
import { Button } from '../components/ui/Button';

export const LandingPage = () => {
  return (
    <div className="flex flex-col min-h-screen bg-background">
      {/* Hero Section */}
      <section className="relative pt-24 pb-32 overflow-hidden border-b border-border">
        <div className="absolute inset-0 bg-gradient-to-br from-primary-50 to-background -z-10" />
        <div className="container mx-auto px-4 text-center max-w-4xl">
          <div className="inline-flex items-center space-x-2 bg-primary-50 text-primary-700 px-4 py-1.5 rounded-full text-sm font-medium mb-8 border border-primary-200 shadow-sm">
            <Activity className="w-4 h-4" />
            <span>The Future of Healthcare Connectivity</span>
          </div>
          <h1 className="text-5xl md:text-7xl font-bold tracking-tight text-foreground mb-8">
            Unified Health Management, <br className="hidden md:block" />
            <span className="text-primary-600">Powered by AI</span>
          </h1>
          <p className="text-xl text-muted-foreground mb-12 max-w-2xl mx-auto text-gray-500">
            Connect patients, doctors, and diagnostic centers through a single, secure digital ecosystem. Real-time history, instant diagnosis, zero delays.
          </p>
          <div className="flex flex-col sm:flex-row items-center justify-center space-y-4 sm:space-y-0 sm:space-x-6">
            <Link to="/register">
              <Button size="lg" className="h-14 px-8 text-lg font-semibold w-full sm:w-auto shadow-md hover:shadow-lg">
                Get Started Free
                <ArrowRight className="ml-2 w-5 h-5" />
              </Button>
            </Link>
            <Link to="/login">
              <Button variant="outline" size="lg" className="h-14 px-8 text-lg font-semibold w-full sm:w-auto bg-white">
                Partner Login
              </Button>
            </Link>
          </div>
        </div>
      </section>

      {/* Problem & Solution */}
      <section className="py-24 bg-white">
        <div className="container mx-auto px-4 max-w-6xl">
          <div className="text-center mb-16">
            <h2 className="text-3xl font-bold text-foreground mb-4">Why MediLink AI?</h2>
            <p className="text-muted-foreground text-lg max-w-2xl mx-auto text-gray-500">
              Healthcare data is fragmented. We solve the delays and risks associated with disconnected medical histories.
            </p>
          </div>
          <div className="grid md:grid-cols-3 gap-8">
            <div className="p-8 rounded-2xl bg-slate-50 border border-border transition-transform hover:-translate-y-1">
              <Clock className="w-12 h-12 text-primary-600 mb-6" />
              <h3 className="text-xl font-bold mb-3 text-foreground">Zero Diagnostic Delays</h3>
              <p className="text-gray-500 leading-relaxed">Instantly access patient records in emergencies, reducing critical gaps in treatment planning.</p>
            </div>
            <div className="p-8 rounded-2xl bg-secondary-50 border border-secondary-200 transition-transform hover:-translate-y-1">
              <FileText className="w-12 h-12 text-secondary-600 mb-6" />
              <h3 className="text-xl font-bold mb-3 text-foreground">Complete Health History</h3>
              <p className="text-gray-500 leading-relaxed">No more carrying files. Unified records mean better context and fewer medication risks.</p>
            </div>
            <div className="p-8 rounded-2xl bg-slate-50 border border-border transition-transform hover:-translate-y-1">
              <Shield className="w-12 h-12 text-primary-600 mb-6" />
              <h3 className="text-xl font-bold mb-3 text-foreground">Bank-Grade Security</h3>
              <p className="text-gray-500 leading-relaxed">Your medical data is encrypted and strictly accessible only to authorized professionals.</p>
            </div>
          </div>
        </div>
      </section>
      
      {/* CTA Bottom Section */}
      <section className="py-24 bg-primary-900 text-white border-t border-primary-800">
        <div className="container mx-auto px-4 text-center max-w-3xl">
          <h2 className="text-4xl font-bold mb-6 text-white">Ready to transform your healthcare experience?</h2>
          <p className="text-primary-100 mb-10 text-lg">
            Join thousands of patients and providers utilizing our unified ecosystem.
          </p>
          <Link to="/register">
            <Button size="lg" className="h-14 px-10 text-lg font-semibold bg-white text-primary-900 hover:bg-gray-100 shadow-xl">
              Create an Account Now
            </Button>
          </Link>
        </div>
      </section>
    </div>
  );
};
