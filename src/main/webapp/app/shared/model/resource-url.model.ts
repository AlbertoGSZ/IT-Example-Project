import { ICaseReport } from 'app/shared/model/case-report.model';

export interface IResourceURL {
  id?: number;
  url?: string;
  caseReport?: ICaseReport;
}

export class ResourceURL implements IResourceURL {
  constructor(public id?: number, public url?: string, public caseReport?: ICaseReport) {}
}
