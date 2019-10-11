export interface IPreIncarcerationRegistry {
  id?: number;
  oldRank?: number;
  oldChief?: number;
  oldSubordinates?: string;
}

export class PreIncarcerationRegistry implements IPreIncarcerationRegistry {
  constructor(public id?: number, public oldRank?: number, public oldChief?: number, public oldSubordinates?: string) {}
}
