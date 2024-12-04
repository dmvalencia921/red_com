import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarouselEntreComponent } from './carousel-entre.component';

describe('CarouselEntreComponent', () => {
  let component: CarouselEntreComponent;
  let fixture: ComponentFixture<CarouselEntreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarouselEntreComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CarouselEntreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
