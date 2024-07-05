import { Component, OnInit } from '@angular/core';
import { BookService } from '../../../../services/services/book.service';
import { PageResponseBookResponse } from '../../../../services/models/page-response-book-response';
import { Router } from '@angular/router';
import {CommonModule, NgForOf, NgIf} from "@angular/common";
import { BookCardComponent } from "../../components/book-card/book-card.component";
import { BookResponse } from "../../../../services/models/book-response";
import {borrowBook} from "../../../../services/fn/book/borrow-book";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  standalone: true,
  imports: [
    BookCardComponent,
    NgForOf,
    NgIf
  ],
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {
  bookResponse: PageResponseBookResponse = {};
  page = 0;
  size = 5;
  pages: any = [];
  message = '';
  level: 'success' | 'error' = 'success';

  constructor(
    private bookService: BookService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.findAllBooks();
  }

  private findAllBooks() {
    this.bookService.findAllBooks({
      page: this.page,
      size: this.size
    })
      .subscribe({
        next: (books) => {
          this.bookResponse = books;
          this.pages = Array(this.bookResponse.totalPages)
            .fill(0)
            .map((x, i) => i);
        }
      });
  }

  goToPage(page: number) {
    this.page = page;
    this.findAllBooks();
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllBooks();
  }

  goToPreviousPage() {
    this.page--;
    this.findAllBooks();
  }

  goToLastPage() {
    this.page = this.bookResponse.totalPages as number - 1;
    this.findAllBooks();
  }

  goToNextPage() {
    this.page++;
    this.findAllBooks();
  }

  get isLastPage() {
    return this.page === this.bookResponse.totalPages as number - 1;
  }

  borrowBookM(book: BookResponse) {
    this.message = '';
    this.level = 'success';
    if (book.id === undefined) {
      console.error('Book ID is undefined');
      this.message = 'Error: Book ID is undefined';
      this.level = 'error';
      return;
    }
    this.bookService.borrowBook(book.id).subscribe({
      next: (result) => {
        this.level = 'success';
        this.message = 'Book successfully added to your list';
      },
      error: (err) => {
        console.log(err);
        this.level = 'error';
        this.message = err.error?.error || 'Error borrowing book';
      }
    });
  }
  
}
