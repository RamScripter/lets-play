import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  hasError: boolean = false;
  error: String = '';

  onSubmit(form: NgForm) {


    if (form.value.password !== 'password') {
      this.hasError = true;
      console.error('Invalid password')
      this.error = 'Invalid password';
      return;
    }
    console.log(form.value);
  }
}
