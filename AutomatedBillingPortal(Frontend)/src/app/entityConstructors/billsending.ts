import { Customers } from "./customers";
import { productmodel } from "./productModel";

export class Billsending{
  constructor(
    public custDetails: Customers,
    public productDetails: productmodel,
    public advancePaid: number
    ){}
}
