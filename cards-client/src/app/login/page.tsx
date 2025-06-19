"use client";

import React, { useState } from "react";
import { Field, Input, Label } from "@headlessui/react";
import Button from "@/components/ui/Button.component";
import { SubmitHandler, useForm } from "react-hook-form";
import { z } from "zod/v4";
import { zodResolver } from "@hookform/resolvers/zod";
import { LoginSchema, RegisterSchema } from "@/lib/auth/LoginData.type";

const errorMessages = {
  server: {
    notAnswers: "Server isn't answering",
    incorrect: "Incorrect login data",
    alreadyExists: "User already exist's",
  },
};

const LoginPage = () => {
  const [isRegistering, setIsRegistering] = useState(false);

  const schema = isRegistering ? RegisterSchema : LoginSchema;

  // Infer form data type depending on both schemas
  type LoginFormData = z.infer<typeof LoginSchema>;
  type RegisterFormData = z.infer<typeof RegisterSchema>;
  type FormData = LoginFormData | RegisterFormData;

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormData>({ resolver: zodResolver(schema) });

  const toggleRegistering = () => {
    setIsRegistering((prev) => !prev);
  };

  const onSubmit: SubmitHandler<FormData> = (data) => console.log(data);

  return (
    <div className="size-full flex justify-center items-center">
      <form
        onSubmit={handleSubmit(onSubmit)}
        className="flex flex-col p-6 py-12 gap-8 justify-center rounded-4xl w-1/3 h-1/2 border-b-8 border-purple-400 border-4 shadow-md/30 shadow-gray-400"
      >
        <div className="flex flex-col gap-4">
          <h1 className="text-center text-6xl text-gray-50">
            {isRegistering ? "Register" : "Login"}
          </h1>
        </div>
        <div className="flex flex-col justify-between h-full">
          <div className="flex flex-col gap-4">
            <Field className="flex flex-col gap-2">
              <Label>Email</Label>
              <Input
                className="bg-gray-700 rounded text-4x p-2"
                {...register("email")}
              />
            </Field>

            <Field className="flex flex-col gap-2">
              <Label>Password</Label>
              <Input
                className="bg-gray-700 rounded text-4x p-2"
                type="password"
                {...register("password")}
              />
            </Field>

            {isRegistering && (
              <Field className="flex flex-col gap-2">
                <Label>Confirm password</Label>
                <Input
                  className="bg-gray-700 rounded text-4x p-2"
                  type="password"
                  {...register("confirmPassword")}
                />
              </Field>
            )}
            <p className="text-red-400">
              {Object.entries(errors).map(([field, error]) => (
                <span key={field}>
                  * {error?.message} <br />
                </span>
              ))}
            </p>
          </div>
          <div className="flex flex-col items-center gap-4">
            <p className="text-center text-gray-300">
              {!isRegistering ? (
                <>
                  <span>Don&apos;t have an account yet? </span>
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

            <Button type="submit" className="flex grow-1 w-full">
              {isRegistering ? "Register" : "Login"}
            </Button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default LoginPage;
