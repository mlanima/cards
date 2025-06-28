'use client';
import Logo from '@/components/ui/Logo';
import { TbCardsFilled } from 'react-icons/tb';
import { FaInfo } from 'react-icons/fa';
import Link from 'next/link';
import Button from '@/components/ui/Button.component';
import { IconType } from 'react-icons/lib';
import { removeToken } from '@/lib/api/auth.service';
import { useAuthStore } from '@/stores/authStore';
import { usePathname } from 'next/navigation';

const navItems = [
    { name: 'Learning', href: '/decks', icon: TbCardsFilled },
    { name: 'About', href: '#', icon: FaInfo },
];

const NavItem = ({
    name,
    href,
    icon: Icon,
    isActive,
}: {
    name: string;
    href: string;
    icon: IconType;
    isActive: boolean;
}) => {
    return (
        <li
            className={`rounded-full p-2 text-lg mx-6 flex items-center gap-2 cursor-pointer
                ${isActive ? 'bg-stone-600 ' : 'hover:bg-stone-700 '}
            `}
        >
            <Link href={href} className="flex items-center text-2xl w-full">
                <Icon className=" text-3xl shrink-0 grow-1" />
                <h2
                    className={`w-0 transition-all duration-300 ease-in-out overflow-hidden
                    group-hover:w-full group-hover:pl-4 `}
                >
                    {name}
                </h2>
            </Link>
        </li>
    );
};

const Sidebar = () => {
    const pathname = usePathname();
    const isLoggedIn = useAuthStore((state) => state.isLoggedIn);
    const logOut = useAuthStore((state) => state.logOut);

    const onButtonClick = () => {
        if (isLoggedIn) {
            removeToken();
            logOut();
        }
    };

    return (
        <aside className="group h-screen bg-stone-800 text-lime-50 w-28 hover:w-72 fixed transition-all duration-300 ease-in-out overflow-hidden">
            <nav className="flex flex-col justify-between h-full overflow-hidden">
                <div>
                    <Logo className="text-2xl" height={64} width={64}></Logo>
                    <ul className="flex flex-col gap-12 w-full overflow-hidden">
                        {navItems.map((item) => (
                            <NavItem
                                key={item.name}
                                name={item.name}
                                href={item.href}
                                icon={item.icon}
                                isActive={pathname === item.href}
                            />
                        ))}
                    </ul>
                </div>
                <Link
                    href="/login"
                    className="h-16 m-4"
                    onClick={onButtonClick}
                >
                    <Button className="w-full h-full">
                        {isLoggedIn ? 'Log out' : 'Log in'}
                    </Button>
                </Link>
            </nav>
        </aside>
    );
};

export default Sidebar;
