import React from 'react';
import Link from 'next/link';
import Image from 'next/image';

interface LogoProps {
    className?: string;
    height: number;
    width: number;
}

const Logo = ({ className, height, width }: LogoProps) => {
    return (
        <Link href="/" className={`${className} flex items-center p-8`}>
            <Image
                src={'/logo_sized.png'}
                alt="Gnoms.io"
                className={`object-contain}`}
                width={width}
                height={height}
            ></Image>
            <h1 className="font-bold overflow-hidden pl-4">
                Gnoms<span className="text-lime-600">.io</span>
            </h1>
        </Link>
    );
};

export default Logo;
