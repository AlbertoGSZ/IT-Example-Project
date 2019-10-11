import { ICaseReport } from 'app/shared/model/case-report.model';

export interface IResourceUrl {
  id?: number;
  urlLink?: string;
  caseReport?: ICaseReport;
}

export class ResourceUrl implements IResourceUrl {
  constructor(public id?: number, public urlLink?: string, public caseReport?: ICaseReport) {}
}
