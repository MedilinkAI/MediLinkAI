import { React,Icons, Profile,Badge,NavLinks } from "../Constants/ImportConstants";

const Header = () => {
  return (
    <div className="w-full h-20 flex text-white justify-between place-items-center p-5">
      <div className="flex gap-1 items-center text-bright-sun-500 font-bold">
        <Icons.Briefcase className="h-10 w-10" stroke={1} />
        <div className="text-3xl">IJOBS</div>
      </div>
     <NavLinks />
      <div className="flex gap-3 items-center">
        <div className="flex gap-5 items-center">
          <div>Suryanshu Jha</div>
          <Profile.Avatar
            className="h-5 w-5 rounded-full"
            size="sm"
            radius="xl"
            src="https://tabler.io/_next/image?url=%2Favatars%2Fdefault%2F8654c911c90383bb42a6cdddd66014c5.png&w=400&q=75"
            alt="Avatar"
          />
        </div>
        <div className="bg-mine-shaft-900 p-1.5 rounded-full">
          <Icons.Settings className="h-5 w-5" stroke={1.5} />
        </div>
        <div className="bg-mine-shaft-900 p-1.5 rounded-full relative">
          <div>
            <Icons.Notification className="h-5 w-5" stroke={1.5} />
          <Badge className="absolute -top-2 -right-2" size="xs" color="brightSun.4" variant="
          filled">
            2
          </Badge>
          </div>
          
        </div>
      </div>
    </div>
  );
};

export default Header;
