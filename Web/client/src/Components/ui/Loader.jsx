import { cn } from './Button';

export const Loader = ({ className, size = 'default' }) => {
  const sizes = {
    sm: 'w-4 h-4 border-2',
    default: 'w-8 h-8 border-4',
    lg: 'w-12 h-12 border-4',
  };

  return (
    <div className={cn("flex justify-center items-center", className)}>
      <div 
        className={cn(
          "animate-spin rounded-full border-primary-600 border-t-transparent", 
          sizes[size]
        )} 
      />
    </div>
  );
};
