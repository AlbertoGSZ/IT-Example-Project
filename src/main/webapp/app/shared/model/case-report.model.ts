import { Moment } from 'moment';
import { IPerson } from 'app/shared/model/person.model';
import { IResourceURL } from 'app/shared/model/resource-url.model';

export interface ICaseReport {
  id?: number;
  date?: Moment;
  personDetails?: string;
  eventDescription?: string;
  person?: IPerson;
  mugshotReport?: IResourceURL;
  evidencePhotos?: IResourceURL[];
}

export class CaseReport implements ICaseReport {
  constructor(
    public id?: number,
    public date?: Moment,
    public personDetails?: string,
    public eventDescription?: string,
    public person?: IPerson,
    public mugshotReport?: IResourceURL,
    public evidencePhotos?: IResourceURL[]
  ) {}
}
