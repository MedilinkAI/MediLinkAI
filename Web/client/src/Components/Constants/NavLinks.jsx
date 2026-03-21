import {React,useLocation,Link} from "./ImportConstants"

const NavLinks = () => {
  const links = [
    { id: 1, name: "Find Jobs", url: "/find-jobs" },
    { id: 2, name: "Post Jobs", url: "/post-jobs" },
    { id: 3, name: "Messages", url: "/messages" },
    { id: 4, name: "About Us", url: "/about-us" },
  ];
  const location = useLocation();
  return (
    <div className="flex gap-5 items-center h-full text-mine-shaft-300">
      {links.map((link, index) => (
        <div  className={`${location.pathname=="/"+link.url?"border-bright-sun-400 text-bright-sun-400":"border-none"} border-t-[3px] h-full items-center flex`}>
          {" "}
          <Link key={index} to={link.url}>
            Find Jobs
          </Link>
        </div>
      ))}
    </div>
  );
};

export default NavLinks;
