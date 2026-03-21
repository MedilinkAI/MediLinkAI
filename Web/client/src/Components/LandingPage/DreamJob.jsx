import React from "react";
import { Inputs, Icons } from "../Constants/ImportConstants";
const DreamJob = () => {
  return (
    <div className="flex px-20 items-center">
      <div className="flex flex-col w-[45%]">
        <div className="text-6xl font-bold leading-tight text-mine-shaft-100 [&>span]:text-bright-sun-400">
          Find your <span>dream</span> <span>job</span> <span>with us</span>
        </div>
        <div className="text-lg text-mine-shaft-200">
          Good life begins with a good company.<br/> Start exploring thousand of jobs
          in one place.
        </div>
        <div className="flex gap-2 mt-5">
          <Inputs.TextInput
            label="Job Title"
            className="bg-mine-shaft-900 rounded-lg py-1 px-2 text-mine-shaft-100 [&_input]:text-mine-shaft-100"
            placeholder="Software Developer"
          />
          <Inputs.TextInput
            label="Job Type"
            className="bg-mine-shaft-900 rounded-lg py-1 px-2 text-mine-shaft-100  [&_input]:text-mine-shaft-100"
            placeholder="Full Time"
          />
          <div className="flex items-center justify-center h-full w-20 bg-bright-sun-400 rounded-lg p-2 hover:to-bright-sun-500 cursor-pointer">
            <Icons.IconSearch className="h-[85%] w-[85%] text-mine-shaft-100" />
          </div>
        </div>
      </div>
      <div className="w-[55%] flex items-center justify-center">
        <div>
          <img src="/Images/Employee.webp" alt="" />
        </div>
      </div>
    </div>
  );
};

export default DreamJob;
