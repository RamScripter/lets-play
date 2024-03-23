export class Ad {
    id!: number;
    createdAt!: Date;
    title!: string;
    userType!: string;
    seeking!: string;
    image: string | undefined;
    style!: string;
    description!: string;
}