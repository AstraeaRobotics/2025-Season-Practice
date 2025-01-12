// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



import frc.robot.Constants.PivotConstants.PivotSpeeds;
import frc.robot.Constants.PivotConstants.PivotStates;
public class PivotSubsystemNew extends SubsystemBase {
  private PIDController pid = new PIDController(0.01, 0, 1);
  /** Creates a new PivotSubsystemNew. */
  DigitalInput limitSwitch;
  CANSparkMax motor1;
  PivotStates state;
  PivotSpeeds speed;
  double speedSetPoint;
  double setPoint;
  double setSpeed1;
  RelativeEncoder m_encoder; 
  
    
  public PivotSubsystemNew() {

      speed=PivotSpeeds.kSlow;
        state=PivotStates.kground1;
        setPoint=state.getPivotSetPoint();
        m_encoder=motor1.getEncoder();
        
    motor1= new CANSparkMax(0, MotorType.kBrushless);
      }

        public void setSpeed(PivotSpeeds newSpeed){
          speed=newSpeed;
          speedSetPoint=newSpeed.getPivotSpeed();
        
        }

        public PivotSpeeds getspeed(){
          return speed;
        }
      public void setState(PivotStates newState){
        state=newState;
        setPoint=newState.getPivotSetPoint();
    
      }

      public PivotStates getState(){
        return state;
      }
      private void configureMotors(){

        m_encoder.setPosition(0);
        m_encoder.setPositionConversionFactor(2*Math.PI);}        
        
        public double getEncoder(){
        return m_encoder.getPosition();}

        public void MotorMove(double newSpeed)
{
    
    
    motor1.set(newSpeed);

}


public boolean getlimitSwitch(){

  return limitSwitch.get();
}
public double getPIDOutput() {
  
  return pid.calculate(m_encoder.getPosition(), state.getPivotSetPoint());
}
  


  @Override
  public void periodic() {
    
    motor1.set(getPIDOutput());
    // This method will be called once per scheduler run
  }}

