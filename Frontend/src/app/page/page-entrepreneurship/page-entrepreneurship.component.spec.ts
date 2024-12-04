import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageEntrepreneurshipComponent } from './page-entrepreneurship.component';

describe('PageEntrepreneurshipComponent', () => {
  let component: PageEntrepreneurshipComponent;
  let fixture: ComponentFixture<PageEntrepreneurshipComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PageEntrepreneurshipComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PageEntrepreneurshipComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
