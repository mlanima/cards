'use client';
import React from 'react';
import Logo from '@/components/ui/Logo';
import Button from '@/components/ui/Button.component';

const Home = () => {
    return (
        <div className="flex justify-center items-center w-full h-full">
            <div className="flex flex-col items-center gap-12 p-8">
                <Logo className=" text-6xl" height={120} width={120} />
                <p className="text-xl w-2xl">
                    Lorem ipsum dolor sit amet consectetur adipisicing elit.
                    Nulla assumenda, accusantium impedit eum reprehenderit esse
                    natus eius error tempora et dolores cumque voluptatem sit,
                    fugiat voluptates consequuntur, quaerat vitae magnam.
                </p>
                <Button>Get Started</Button>
            </div>
        </div>
    );
};

export default Home;
