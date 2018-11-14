import {Component} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {

  category = 'technology';

  constructor(private router: Router) {
  }

  navigateToArticles() {
    this.router.navigateByUrl(`/news/${this.category}`);
  }
}
