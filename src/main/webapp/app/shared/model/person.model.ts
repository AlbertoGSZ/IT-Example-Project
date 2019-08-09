import { Moment } from 'moment';
import { IPerson } from 'app/shared/model/person.model';
import { ICaseReport } from 'app/shared/model/case-report.model';

export const enum Nationality {
  NORTH_AMERICAN = 'NORTH_AMERICAN',
  ENGLISH = 'ENGLISH',
  SPANISH = 'SPANISH',
  FRENCH = 'FRENCH',
  CANADIAN = 'CANADIAN',
  ITALIAN = 'ITALIAN',
  RUSSIAN = 'RUSSIAN'
}

export const enum Sex {
  Male = 'Male',
  Female = 'Female'
}

export interface IPerson {
  id?: number;
  name?: string;
  surname?: string;
  birthDate?: Moment;
  age?: Moment;
  adress?: string;
  nationality?: Nationality;
  sex?: Sex;
  alias?: string;
  rank?: number;
  subordinates?: IPerson[];
  felonyRecordsDigitals?: ICaseReport[];
  chief?: IPerson;
}

export class Person implements IPerson {
  constructor(
    public id?: number,
    public name?: string,
    public surname?: string,
    public birthDate?: Moment,
    public age?: Moment,
    public adress?: string,
    public nationality?: Nationality,
    public sex?: Sex,
    public alias?: string,
    public rank?: number,
    public subordinates?: IPerson[],
    public felonyRecordsDigitals?: ICaseReport[],
    public chief?: IPerson
  ) {}
}
