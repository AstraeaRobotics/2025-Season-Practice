// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.pivot;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.Constants.PivotConstants.PivotSpeeds;
import frc.robot.Constants.PivotConstants.PivotStates;
import frc.robot.subsystems.PivotSubsystemNew;
public class MoveToPivotState extends Command {
  /** Creates a new MoveToPivotState. 
   * @param d 
   * @param pivotSub2 */
  private PivotSubsystemNew pivotSub1;
    private PivotStates state;
    private double pivotSetPoint;
    private double currentSetPoint;
    private double pivotSpeed;
    private PivotSpeeds speed;
  public MoveToPivotState(PivotSubsystemNew pivotSub1, PivotSpeeds speed) {
    
    this.speed=speed;
     
      this.pivotSub1=pivotSub1;
      
      addRequirements(pivotSub1);
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    

    state=pivotSub1.getState();
    pivotSetPoint=state.getPivotSetPoint();
    pivotSpeed=speed.getPivotSpeed();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentSetPoint=pivotSub1.getEncoder();
    if (pivotSetPoint>currentSetPoint){
      pivotSub1.MotorMove(pivotSpeed);
    }
    else if (pivotSetPoint<currentSetPoint){
      pivotSub1.MotorMove(pivotSpeed);
    }
    else {
      pivotSub1.MotorMove(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pivotSub1.MotorMove(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(currentSetPoint-pivotSetPoint)<0.5)
    return true;
    else {return false;}
  }
}
