import React from 'react';

const page = () => {
    return (
        <div className="flex justify-center h-full w-full">
            <div className="contaner">
                <div className="search"></div>
                <div className="sections">
                    <section className="">
                        <h2>Decks</h2>
                        <div className="flex flex-wrap gap-6"></div>
                    </section>
                </div>
            </div>
        </div>
    );
};

export default page;
