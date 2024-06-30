/*import { Component } from '@angular/core';
import {AuthenticationRequest} from "../../services/models/authentication-request";
import {FormsModule} from "@angular/forms";
import {Router} from '@angular/router';
import {AuthenticationService} from "../../services/services/authentication.service";
import {CommonModule} from "@angular/common";
import {TokenService} from "../../services/token/token.service";
*/

import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/services/authentication.service';
import {AuthenticationRequest} from '../../services/models/authentication-request';
import {TokenService} from '../../services/token/token.service';
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

    authRequest: AuthenticationRequest = {email: '', password: ''};
    errorMsg: Array<string> = [];

    constructor(
      private router: Router,
      private authService: AuthenticationService,
      private tokenService: TokenService
    ) {
    }

  login() {
    this.errorMsg = [];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: (res) => {
        this.tokenService.token = res.token as string;
        this.router.navigate(['books']);
      },
      error: (err) => {
        console.log('Full error object:', err);

        if (err.error && err.error.validatingErrors) {
          this.errorMsg = err.error.validatingErrors;
        } else if (err.error && typeof err.error === 'string') {
          this.errorMsg = [err.error];
        } else {
          this.errorMsg = ['An unknown error occurred'];
        }

        console.log('Error messages:', this.errorMsg);

      }
    });
  }

  register() {
      this.router.navigate(['register'])
  }
}
