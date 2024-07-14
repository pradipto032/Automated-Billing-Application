import { Address } from "./address";

export class Customers{
  constructor(
    public custName: string,
    public custPhone: string,
    public custMail: string,
    public custShippingAddress: Address,
    public custBillingAddress: Address
  ){}
}
