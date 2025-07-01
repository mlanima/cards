'use client';
import { Geist, Geist_Mono } from 'next/font/google';
import './globals.css';
import Sidebar from '@/components/layout/Sidebar.component';
import { useDialogStore } from '@/stores/dialogStore';
import Dialog from '@/components/ui/Dialog.component';
import { HeroUIProvider } from '@heroui/react';

const geistSans = Geist({
    variable: '--font-geist-sans',
    subsets: ['latin'],
});

const geistMono = Geist_Mono({
    variable: '--font-geist-mono',
    subsets: ['latin'],
});

export default function RootLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    const dialogState = useDialogStore();
    return (
        <html lang="en">
            <body
                className={`flex min-h-screen h-auto bg-white text-black
                    ${geistSans.variable} ${geistMono.variable} antialiased overflow-auto`}
            >
                {dialogState.dialog && (
                    <Dialog
                        message={dialogState.dialog.message}
                        isPositive={dialogState.dialog.isPositive}
                    />
                )}
                <Sidebar />^
                <div className="pl-28 min-h-screen w-full">{children}</div>
            </body>
        </html>
    );
}
