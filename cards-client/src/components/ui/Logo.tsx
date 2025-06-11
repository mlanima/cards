import React from "react";
import Link from "next/link";

interface LogoProps {
  className?: string;
}

const Logo = ({ className }: LogoProps) => {
  return (
    <Link href="/" className={`${className}`}>
      <span>Cards</span>
      <span className="text-purple-400">.io</span>
    </Link>
  );
};

export default Logo;
