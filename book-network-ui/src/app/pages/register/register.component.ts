import { Component, ChangeDetectorRef } from '@angular/core';
import { RegistrationRequest } from "../../services/models/registration-request";
import { NgForOf, NgIf } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Router } from "@angular/router";
import { AuthenticationService } from "../../services/services/authentication.service";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  registerRequest: RegistrationRequest = {
    email: '',
    firstname: '',
    lastname: '',
    password: ''
  };
  errorMsg: string[] = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private cdr: ChangeDetectorRef
  ) {}

  register() {
    console.log('Sending registration request:', this.registerRequest);
    this.errorMsg = [];
    this.authService.register({
      body: this.registerRequest
    }).subscribe({
      next: () => {
        console.log('Registration successful');
        this.router.navigate(['activate-account']);
      },
      error: (err) => {
        console.log('Full error object:', err);
        if (err.error && err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else if (err.error && err.error.validatingErrors) {
          // Добавляем проверку на validatingErrors
          this.errorMsg = err.error.validatingErrors;
        } else if (err.error && typeof err.error === 'string') {
          this.errorMsg = [err.error];
        } else if (err.error && err.error.message) {
          this.errorMsg = [err.error.message];
        } else {
          this.errorMsg = ['An unknown error occurred'];
        }
        console.log('Error messages:', this.errorMsg);
        this.cdr.detectChanges();
      }
    });
  }

  login() {
    this.router.navigate(['login']);
  }
}
