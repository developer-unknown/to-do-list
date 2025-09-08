import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity({ name: 'tasks' })
export class Task {

    @PrimaryGeneratedColumn('increment')
    id: number;

    @Column('varchar')
    title: string;

    @Column('text', {
        nullable: true,
    })
    description: string;

    @Column('varchar')
    state: string;

    @Column('timestamp')
    createAt: Date;
}
