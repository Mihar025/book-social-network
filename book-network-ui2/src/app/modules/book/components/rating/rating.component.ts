import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  standalone: true,
  imports: [
    NgForOf,
    NgIf
  ],
  styleUrls: ['./rating.component.scss']
})
export class RatingComponent {
    @Input() rating : number = 0;
    @Output() ratingClicked: EventEmitter<number> = new EventEmitter<number>();
    maxRating: number = 5;

    get fullStars(): number {
      return Math.floor(this.rating);
    }

    get halfStar(): boolean {
      return this.rating % 1 !== 0;
    }

    get emptyStar(): number {
      return this.maxRating - Math.ceil(this.rating);
    }

}
