import { cn } from './Button';

export const FormWrapper = ({ title, description, children, onSubmit, className }) => {
  return (
    <div className={cn("w-full max-w-md mx-auto bg-card text-card-foreground rounded-xl shadow-soft border border-border overflow-hidden", className)}>
      <div className="flex flex-col space-y-1.5 p-6">
        {title && <h3 className="font-semibold tracking-tight text-2xl text-center">{title}</h3>}
        {description && <p className="text-sm text-muted-foreground text-center text-gray-500">{description}</p>}
      </div>
      <div className="p-6 pt-0">
        <form onSubmit={onSubmit} className="space-y-4">
          {children}
        </form>
      </div>
    </div>
  );
};
