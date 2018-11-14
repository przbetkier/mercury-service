import {Article} from './article.model';

export class NewsResponse {
  constructor(public country: string,
              public category: string,
              public articles: Article[]) {
  }
}
