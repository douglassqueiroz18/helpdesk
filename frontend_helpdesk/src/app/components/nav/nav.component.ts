import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  standalone: false,
  templateUrl:'nav.component.html',
  styleUrls: ['nav.component.css'],
})
export class NavComponent  implements OnInit {
  constructor( private router: Router) { }

  ngOnInit(): void {
    // Initialization logic here
    this.router.navigate(['/tecnicos']);
  }
  // Add any additional methods or properties as needed

}
