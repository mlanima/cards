'use client';

import React, { useState } from 'react';
import { Field, Input, Label } from '@headlessui/react';
import Button from '@/components/ui/Button.component';
import { SubmitHandler, useForm } from 'react-hook-form';
import { z } from 'zod/v4';
import { zodResolver } from '@hookform/resolvers/zod';
import { LoginSchema, RegisterSchema } from '@/lib/models/auth.types';
import Image from 'next/image';
import { login, register as reg } from '@/lib/api/auth.service';
import { useRouter } from 'next/navigation';
import { useAuthStore } from '@/stores/authStore';
import { useDialogStore } from '@/stores/dialogStore';

const LoginPage = () => {
    const router = useRouter();
    const [isRegistering, setIsRegistering] = useState(false);
    const [isHovered, setIsHovered] = useState(false);
    const authState = useAuthStore();
    const { show } = useDialogStore();

    const schema = isRegistering ? RegisterSchema : LoginSchema;

    // Infer form data type depending on both schemas
    type LoginFormData = z.infer<typeof LoginSchema>;
    type RegisterFormData = z.infer<typeof RegisterSchema>;
    type FormData = LoginFormData | RegisterFormData;

    const {
        register,
        handleSubmit,
        reset,
        formState: { errors },
    } = useForm<FormData>({ resolver: zodResolver(schema) });

    const toggleRegistering = () => {
        setIsRegistering((prev) => !prev);
    };

    const onSubmit: SubmitHandler<FormData> = (data) => {
        console.log('submiting: ' + JSON.stringify(data));
        const submit = isRegistering ? reg : login;

        submit(data.email, data.password)
            .then(() => {
                if (isRegistering) {
                    setIsRegistering(false);
                    reset();
                    show("You've registered!", true);
                } else {
                    authState.logIn();
                    router.push('/decks');
                    authState.logIn();
                    show("You're logged in!", true);
                }
            })
            .catch((reason: Error) => {
                console.log(reason);
                show(reason.message, false);
            });
    };

    return (
        <div className="size-full flex justify-center items-center">
            <form
                onSubmit={handleSubmit(onSubmit)}
                className="flex p-12 px-16 ps-12 justify-between h-2/3 w-3/4 bg-lime-50  rounded-4xl border-b-8 border-lime-700 border-2 shadow-2xl/60 font-semibold text-lg text-stone-950"
            >
                <div className="w-1/2 flex justify-start items-center">
                    <Image
                        onMouseEnter={() => setIsHovered(true)}
                        onMouseLeave={() => setIsHovered(false)}
                        src={isHovered ? '/Gnome.png' : '/Gnome_hover.png'}
                        alt="Gnome"
                        width={500}
                        height={500}
                        className="transition-all duration-100"
                    ></Image>
                    <div className=""></div>
                </div>
                <div className="flex flex-col justify-center w-1/2">
                    <div className="flex flex-col gap-4">
                        <h1 className="text-center text-5xl text-lime-700">
                            {isRegistering ? 'Register' : 'Login'}
                        </h1>
                    </div>
                    <div className="flex flex-col justify-between h-fit">
                        <div className="flex flex-col gap-4">
                            <Field className="flex flex-col gap-2">
                                <Label>Email</Label>
                                <Input
                                    className=" bg-stone-700 text-lime-50 rounded text-4x p-2"
                                    {...register('email')}
                                />
                            </Field>

                            <Field className="flex flex-col gap-2">
                                <Label>Password</Label>
                                <Input
                                    className="bg-stone-700 text-lime-50 rounded text-4x p-2"
                                    type="password"
                                    {...register('password')}
                                />
                            </Field>

                            {isRegistering && (
                                <Field className="flex flex-col gap-2">
                                    <Label>Confirm password</Label>
                                    <Input
                                        className="bg-stone-700 text-lime-50 rounded text-4x p-2"
                                        type="password"
                                        {...register('confirmPassword')}
                                    />
                                </Field>
                            )}
                            <p className="text-red-400">
                                {Object.entries(errors).map(
                                    ([field, error]) => (
                                        <span key={field}>
                                            * {error?.message} <br />
                                        </span>
                                    )
                                )}
                            </p>
                        </div>
                        <div className="flex flex-col items-center gap-4">
                            <p className="text-center">
                                {!isRegistering ? (
                                    <>
                                        <span>
                                            Don&apos;t have an account yet?{' '}
                                        </span>
                                        <button
                                            type="button"
                                            onClick={toggleRegistering}
                                            className="text-blue-500 inline-block underline hover:text-blue-400 hover:cursor-pointer"
                                        >
                                            Let&apos;s create one together
                                        </button>
                                    </>
                                ) : (
                                    <button
                                        onClick={toggleRegistering}
                                        className="text-blue-500 inline-block underline hover:text-blue-400 hover:cursor-pointer"
                                    >
                                        You alredy have an account?
                                    </button>
                                )}
                            </p>

                            <Button
                                type="submit"
                                className="flex grow-1 w-full"
                            >
                                {isRegistering ? 'Register' : 'Login'}
                            </Button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default LoginPage;
