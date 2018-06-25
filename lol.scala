extern crate kompics;
use kompics::*;
use std::sync::Arc;
use std::{thread, time};
use kompics::*;
#[derive(Clone, Debug)]
struct X1;
#[derive(Clone, Debug)]
struct X2;
struct X3;
#[derive(ComponentDefinition, Actor)]
struct X4 {
    x5: ComponentContext,
    x6: RequiredPort<X3, X4>,
    x7: ProvidedPort<X3, X4>,
    x8: i32,
}
impl Port for X4 {
    type Indication = X2;
    type Request = X1;
}
impl X4 {
    fn X9() -> X4 {
        X4 {
            x5: ComponentContext::new(),
            x6: RequiredPort::new(),
            x7: ProvidedPort::new(),
        }
    }
}
impl Provide<ControlPort> for X4 {
    fn handle(&self, x12: ControlEvent) -> () {
        match x12 {
            ControlEvent::Start => {
                println!("Starting Pinger... {}", self.test);
            }
            _ => {}
        }
    }
}
impl Require<X3> for X4 {
    fn handle(&self, x15: X2) -> () {
        println!("Got a pong!")
    }
}
impl Provide<X3> for X4 {
    fn handle(&self, x18: X2) -> () {
        println!("Got a ping!")
    }
}
fn main() -> () {
    let x20 = KompicsConfig::new();
    {
        x20.throughput(5)
    };
    let x21 = KompicsSystem::new(x20);
    let x22 = x21.create(move || X4::X9());
    x21.start(&x22);
    x21.trigger_i(X2, x22.on_definition(|x23: X4| x23.x6.share));
    thread::sleep(time::Duration::from_millis(5000));
}
