import { IconBriefcase,IconBellPlus,IconSettings,IconSearch } from '@tabler/icons-react';
import { Avatar,Badge,TextInput } from "@mantine/core";
import React from "react";
import { BrowserRouter, Routes, Route,Link, useLocation } from "react-router-dom";
import NavLinks from "./NavLinks";
import DreamJob from '../LandingPage/DreamJob';
import Header from '../Header/Header';

export { React, Badge, BrowserRouter, Routes, Route, NavLinks,Link, useLocation,Header,DreamJob};
// Icons
export const Icons = {
    Briefcase: IconBriefcase,
    Notification : IconBellPlus,
    Settings : IconSettings,
    IconSearch : IconSearch
  };

  //Pictures
  export const Profile = {
    Avatar : Avatar
  }

  export const Inputs = {
    TextInput : TextInput
  }