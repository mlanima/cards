import { Card } from '@/lib/api/card.service';
import React, { useState, useEffect } from 'react';
import { motion, AnimatePresence } from 'framer-motion';

type CardComponentProps = Card & { swipeDirection: 'left' | 'right' };

const swipeVariants = {
    enter: (direction: 'left' | 'right') => ({
        x: direction === 'right' ? 300 : -300,
        opacity: 0,
    }),
    center: {
        x: 0,
        opacity: 1,
    },
    exit: (direction: 'left' | 'right') => ({
        x: direction === 'right' ? -300 : 300,
        opacity: 0,
    }),
};

const CardComponent = ({ phrase, translation, swipeDirection }: CardComponentProps) => {
    const [isFlipped, setIsFlipped] = useState(false);
    // Reset flip state on card change
    useEffect(() => {
        setIsFlipped(false);
    }, [phrase, translation]);

    const onFlip = () => {
        setIsFlipped((prev: boolean) => !prev);
    };

    return (
        <div className="flex justify-center items-center font-bold h-full w-full">
            <AnimatePresence custom={swipeDirection} mode="wait">
                <motion.div
                    key={phrase + translation}
                    custom={swipeDirection}
                    variants={swipeVariants}
                    initial="enter"
                    animate="center"
                    exit="exit"
                    transition={{ duration: 0.5, ease: 'easeInOut' }}
                    className="flex justify-center items-center h-4/5 w-full mx-4"
                >
                    <div
                        onClick={onFlip}
                        className="relative w-full h-full cursor-pointer"
                        style={{
                            transformStyle: 'preserve-3d',
                            transition: 'transform 0.6s',
                            transform: `rotateY(${isFlipped ? 180 : 0}deg)`
                        }}
                    >
                        {/* Front side (phrase) */}
                        <div
                            className="absolute inset-0 flex rounded-3xl text-5xl justify-center items-center p-4 bg-radial from stroke-neutral-50 to-stone-300 backface-hidden"
                            style={{ backfaceVisibility: 'hidden' }}
                        >
                            {phrase}
                        </div>

                        {/* Back side (translation) */}
                        <div
                            className="absolute inset-0 flex rounded-3xl text-5xl text-stone-100 justify-center items-center p-4 bg-stone-950 backface-hidden"
                            style={{
                                backfaceVisibility: 'hidden',
                                transform: 'rotateY(180deg)',
                            }}
                        >
                            {translation}
                        </div>
                    </div>
                </motion.div>
            </AnimatePresence>
        </div>
    );
};

export default CardComponent;
