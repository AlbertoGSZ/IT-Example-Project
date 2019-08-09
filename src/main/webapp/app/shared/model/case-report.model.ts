import { Moment } from 'moment';
import { IPerson } from 'app/shared/model/person.model';

export interface ICaseReport {
  id?: number;
  date?: Moment;
  mugshot?: string;
  personDetails?: string;
  eventDescription?: string;
  evidencePhotos?: string;
  person?: IPerson;
}

export class CaseReport implements ICaseReport {
  constructor(
    public id?: number,
    public date?: Moment,
    public mugshot?: string,
    public personDetails?: string,
    public eventDescription?: string,
    public evidencePhotos?: string,
    public person?: IPerson
  ) {}
}
