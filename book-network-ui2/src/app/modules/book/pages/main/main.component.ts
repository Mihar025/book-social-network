import { Component } from '@angular/core';
import {MenuComponent} from "../../components/menu/menu.component";
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  standalone: true,
  imports: [
    MenuComponent,
    RouterOutlet
  ],
  styleUrls: ['./main.component.scss']
})
export class MainComponent {

}
