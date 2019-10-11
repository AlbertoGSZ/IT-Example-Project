import { Moment } from 'moment';
import { IPreIncarcerationRegistry } from 'app/shared/model/pre-incarceration-registry.model';
import { IPerson } from 'app/shared/model/person.model';
import { ICaseReport } from 'app/shared/model/case-report.model';

export const enum Nationality {
  NORTH_AMERICAN = 'NORTH_AMERICAN',
  ENGLISH = 'ENGLISH',
  SPANISH = 'SPANISH',
  FRENCH = 'FRENCH',
  CANADIAN = 'CANADIAN',
  ITALIAN = 'ITALIAN',
  RUSSIAN = 'RUSSIAN',
  UNKNOWN = 'UNKNOWN'
}

export const enum Status {
  IMPRISONED = 'IMPRISONED',
  DEAD = 'DEAD',
  ALIVE = 'ALIVE',
  UNKNOWN = 'UNKNOWN'
}

export const enum Sex {
  MALE = 'MALE',
  FEMALE = 'FEMALE',
  UNKNOWN = 'UNKNOWN'
}

export interface IPerson {
  id?: number;
  name?: string;
  surname?: string;
  birthDate?: Moment;
  address?: string;
  age?: number;
  nationality?: Nationality;
  status?: Status;
  sex?: Sex;
  alias?: string;
  rank?: number;
  preIncarcerationRegistry?: IPreIncarcerationRegistry;
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
    public address?: string,
    public age?: number,
    public nationality?: Nationality,
    public status?: Status,
    public sex?: Sex,
    public alias?: string,
    public rank?: number,
    public preIncarcerationRegistry?: IPreIncarcerationRegistry,
    public subordinates?: IPerson[],
    public felonyRecordsDigitals?: ICaseReport[],
    public chief?: IPerson
  ) {}
}
