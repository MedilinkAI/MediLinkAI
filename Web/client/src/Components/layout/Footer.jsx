import { Activity } from 'lucide-react';
import { Link } from 'react-router-dom';

export const Footer = () => {
  return (
    <footer className="border-t border-border bg-background py-12 md:py-16">
      <div className="container mx-auto px-4">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-8">
          <div className="col-span-1 md:col-span-2">
            <Link to="/" className="flex items-center space-x-2 mb-4">
              <Activity className="h-6 w-6 text-primary-600" />
              <span className="font-bold text-xl">MediLink AI</span>
            </Link>
            <p className="text-muted-foreground text-sm leading-relaxed max-w-xs text-gray-500">
              Connecting patients, doctors, and diagnostic centers through a unified, secure, and AI-powered healthcare digital ecosystem.
            </p>
          </div>
          <div>
            <h4 className="font-semibold mb-4 text-foreground">Platform</h4>
            <ul className="space-y-2 text-sm text-gray-500">
              <li><Link to="#" className="hover:text-primary-600 transition-colors">For Patients</Link></li>
              <li><Link to="#" className="hover:text-primary-600 transition-colors">For Doctors</Link></li>
              <li><Link to="#" className="hover:text-primary-600 transition-colors">Diagnostic Centers</Link></li>
              <li><Link to="#" className="hover:text-primary-600 transition-colors">Security</Link></li>
            </ul>
          </div>
          <div>
            <h4 className="font-semibold mb-4 text-foreground">Company</h4>
            <ul className="space-y-2 text-sm text-gray-500">
              <li><Link to="#" className="hover:text-primary-600 transition-colors">About Us</Link></li>
              <li><Link to="#" className="hover:text-primary-600 transition-colors">Careers</Link></li>
              <li><Link to="#" className="hover:text-primary-600 transition-colors">Contact Support</Link></li>
              <li><Link to="#" className="hover:text-primary-600 transition-colors">Privacy Policy</Link></li>
            </ul>
          </div>
        </div>
        <div className="mt-12 pt-8 border-t border-border text-center text-sm text-gray-500 flex flex-col md:flex-row justify-between items-center">
          <p>© {new Date().getFullYear()} MediLink AI. All rights reserved.</p>
          <div className="mt-4 md:mt-0 space-x-4">
             <Link to="#" className="hover:text-primary-600 transition-colors">Terms of Service</Link>
             <Link to="#" className="hover:text-primary-600 transition-colors">Privacy</Link>
          </div>
        </div>
      </div>
    </footer>
  );
};
