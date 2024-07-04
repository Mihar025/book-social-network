import { Component } from '@angular/core';
import { Router } from "@angular/router";
import { AuthenticationService } from "../../services/services/authentication.service";
import { CodeInputModule } from "angular-code-input";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-activate-account',

  imports: [
    CodeInputModule,
    CommonModule
  ],
  templateUrl: './activate-account.component.html',
  standalone: true,
  styleUrls: ['./activate-account.component.scss']  // Изменено здесь
})
export class ActivateAccountComponent {
  message = '';
  isOkay = true;
  submitted = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {}

  onCodeCompleted(token: string) {
    this.confirmationAccount(token);
  }

  redirectToLogin() {
    this.router.navigate(['login']);
  }

  private confirmationAccount(token: string) {
    this.authService.confirm({
      token
    }).subscribe({
      next: () => {
        this.isOkay = true;
        this.message = 'Your account has been successfully activated. Now you can proceed to login';
        this.submitted = true;
      },
      error: () => {
        this.isOkay = false;
        this.message = 'Token has been expired or invalid';
        this.submitted = true;
      }
    });
  }

  tryAgain() {
    this.submitted = false;
    this.message = '';
  }
}

