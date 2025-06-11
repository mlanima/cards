import React from "react";
import Logo from "@/components/ui/Logo";
import { TbCardsFilled } from "react-icons/tb";
import { FaInfo } from "react-icons/fa";
import Link from "next/link";
import { Button } from "@headlessui/react";
import { IconType } from "react-icons/lib";

const navItems = [
  { name: "Learning", href: "/learning", icon: TbCardsFilled },
  { name: "About", href: "#", icon: FaInfo },
];

const NavItem = ({
  name,
  href,
  icon: Icon,
}: {
  name: string;
  href: string;
  icon: IconType;
}) => {
  return (
    <li className="rounded-full text-lg mx-6 hover:bg-gray-800 px-8 py-2">
      <Link
        href={href}
        className="width-fit flex justify-center items-center gap-2 text-2xl"
      >
        <Icon className="flex text-3xl shrink-0" />
        <span className="w-0 opacity-0 group-hover:w-full group-hover:opacity-100 transition-all duration-300 ease-in-out">
          {name}
        </span>
      </Link>
    </li>
  );
};

const Sidebar = () => {
  return (
    <div className="group flex flex-col bg-gray-900 text-white w-36 hover:w-72 fixed min-h-full transition-all duration-300 ease-in-out">
      <Logo className="flex rounded-full justify-center self-center text-2xl font-semibold shadow-xs shadow-black border-b border-purple-400 justify-self-start p-6 m-4 w-full" />
      <nav className="justtify-self-start mt-8">
        <ul className="flex flex-col gap-12">
          {navItems.map((item) => (
            <NavItem
              key={item.name}
              name={item.name}
              href={item.href}
              icon={item.icon}
            ></NavItem>
          ))}
        </ul>
      </nav>
      <Button className="flex justify-center mt-auto items-center gap-2 rounded-md bg-purple-500 px-3 py-1.5 text-xl font-semibold text-white shadow-inner shadow-white/10 focus:not-data-focus:outline-none data-focus:outline data-focus:outline-white data-hover:bg-gray-600 data-open:bg-gray-700 m-4">
        Login
      </Button>
    </div>
  );
};

export default Sidebar;
