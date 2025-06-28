'use client';
import CardComponent from './Card.component';
import { useParams } from 'next/navigation';
import { ChevronRightIcon, ChevronLeftIcon } from '@heroicons/react/24/solid';

function Cards() {
    const params = useParams();
    const id = Number(params?.id);

    return (
        <div className="flex justify-center h-full">
            <div className="container flex-col justify-between h-screen">
                <h1 className="text-2xl">nmae</h1>
                <div className="card-container grow-1 h-5/6 p-6 flex justify-between items-center l">
                    <button className="cursor-pointer ">
                        <ChevronLeftIcon className="hover:scale-110 hover:font-bold size-24 text-stone-400  hover:text-stone-900 transition-all" />
                    </button>
                    <CardComponent
                        id={id}
                        phrase="New Gnom"
                        translation="Gnimka"
                    />

                    <button className="cursor-pointer">
                        <ChevronRightIcon className="hover:scale-110 size-24 text-stone-400 hover:text-stone-900 transition-all" />
                    </button>
                </div>

                <div className="flex justify-center gap-4"></div>
            </div>
        </div>
    );
}

export default Cards;
