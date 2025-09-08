import { Module } from '@nestjs/common';
import { TaskModule } from './task/task.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Task } from './task/entities/task.entity';
import { ConfigModule, ConfigService } from '@nestjs/config';

@Module({
  imports: [
    TaskModule,
    ConfigModule.forRoot(),
    TypeOrmModule.forRoot({
      type: 'mysql',
      port: 3306,
      host: process.env.DB_HOST,
      database: process.env.DB_DATABASE,
      username: process.env.DB_USERNAME,
      password: '',
      entities: [Task],
      synchronize: true,
    }),
  ],
  controllers: [],
  providers: [],
})
export class AppModule {
  constructor(
    private readonly configService: ConfigService,
  ) {}
}
