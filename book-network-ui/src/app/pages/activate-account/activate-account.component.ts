import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";
import {CodeInputModule} from "angular-code-input";

@Component({
  selector: 'app-activate-account',
  standalone: true,
  imports: [
    CodeInputModule
  ],
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.scss'
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
        this.message = 'Your account has been successfully activated.\nNow you can proceed to login'
        this.submitted = true;
      },
      error: () => {
        this.message = 'Token has been expired or invalid'
        this.submitted = true;
      }
    });
  }
}
