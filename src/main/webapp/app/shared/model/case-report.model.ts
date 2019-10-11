import { Moment } from 'moment';
import { IResourceUrl } from 'app/shared/model/resource-url.model';
import { IPerson } from 'app/shared/model/person.model';

export interface ICaseReport {
  id?: number;
  date?: Moment;
  mugshotURL?: string;
  personDetails?: string;
  eventDescription?: string;
  evidencePhotosURL?: string;
  felonyRecordsPDFsURLs?: IResourceUrl[];
  caseReportHolder?: IPerson;
}

export class CaseReport implements ICaseReport {
  constructor(
    public id?: number,
    public date?: Moment,
    public mugshotURL?: string,
    public personDetails?: string,
    public eventDescription?: string,
    public evidencePhotosURL?: string,
    public felonyRecordsPDFsURLs?: IResourceUrl[],
    public caseReportHolder?: IPerson
  ) {}
}
