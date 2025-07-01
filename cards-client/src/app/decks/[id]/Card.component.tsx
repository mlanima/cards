import { Card} from '@/lib/api/card.service';
import React, { useState } from 'react';
import { motion } from 'framer-motion';

const CardComponent = ({ phrase, translation }: Card) => {
    const [isFlipped, setIsFlipped] = useState(false);

    const onFlip = () => {
        setIsFlipped((prev: boolean) => !prev);
    };

    return (
        <div className="flex justify-center items-center font-bold h-5/6 w-5/6 perspective-1000">
            <motion.button
                onClick={onFlip}
                className="relative w-full h-full cursor-pointer"
                style={{ transformStyle: 'preserve-3d' }}
                animate={{ rotateY: isFlipped ? 180 : 0 }}
                transition={{ duration: 0.6, ease: 'easeInOut' }}
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
            </motion.button>
        </div>
    );
};

export default CardComponent;
