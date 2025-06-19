import { z } from "zod/v4";

export const LoginSchema = z.object({
  email: z.email("Invalid email format"),
  password: z.string().min(8, "Password should have at least 8 characters"),
});

export const RegisterSchema = z
  .object({
    email: z.email("Invalid email format"),
    password: z.string().min(8, "Password should have at least 8 characters"),
    confirmPassword: z.string(),
  })
  .refine((data) => data.password === data.confirmPassword, {
    path: ["confirmPassword"],
    message: "Passwords do not match",
  });
