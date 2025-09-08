import { IsEnum, IsNotEmpty, IsOptional, IsString } from "class-validator";

export class CreateTaskDto {

    @IsNotEmpty()
    @IsString()
    title: string;

    @IsOptional()
    @IsString()
    description: string;

    @IsOptional()
    @IsEnum(['Completed', 'Pending', 'In progress'])
    state;
}
