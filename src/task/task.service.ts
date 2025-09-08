import { Injectable, NotFoundException } from '@nestjs/common';
import { CreateTaskDto } from './dto/create-task.dto';
import { UpdateTaskDto } from './dto/update-task.dto';
import { InjectRepository } from '@nestjs/typeorm';
import { Task } from './entities/task.entity';
import { Repository } from 'typeorm';

@Injectable()
export class TaskService {

  constructor(
    @InjectRepository(Task)
    private readonly taskRepository: Repository<Task>,
  ) {}

  async create(createTaskDto: CreateTaskDto) {
    const { state = 'Pending' } = createTaskDto;
    const task = this.taskRepository.create({
      ...createTaskDto, 
      state,
      createAt: new Date(),
    });
    await this.taskRepository.save(task);
    return task;
  }

  async findAll() {
    return await this.taskRepository.find();
  }

  async findState( state ) {
    return this.taskRepository.find({
      where: {state},
    })
  }

  async findOne(id: number) {
    const task = await this.taskRepository.findOneBy({ id });

    if ( !task )
      throw new NotFoundException(`Task with id ${id} does not exist`);

    return task;
  }

  async update(id: number, updateTaskDto: UpdateTaskDto) {
    const result = await this.taskRepository.update(id, updateTaskDto);

    if ( result.affected === 0 )
      throw new NotFoundException(`Task with id ${id} does not exist`);

    return this.findOne(id);
  }

  async remove(id: number) {
    const result = await this.taskRepository.delete(id);

    if ( result.affected === 0 )
      throw new NotFoundException(`Task with id ${id} does not exist`);
  }
}
