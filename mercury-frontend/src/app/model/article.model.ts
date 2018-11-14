export class Article {
  constructor(public author: string,
              public title: string,
              public description: string,
              public date: Date,
              public sourceName: string,
              public articleUrl: string,
              public imageUrl: string) {
  }
}
