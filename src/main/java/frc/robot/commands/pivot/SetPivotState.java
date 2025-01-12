// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.pivot;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.PivotConstants.PivotStates;
import frc.robot.subsystems.PivotSubsystemNew;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SetPivotState extends InstantCommand {
  private PivotSubsystemNew pivotSub;
  private PivotStates state;
  
    
    public SetPivotState(PivotSubsystemNew pivotSub, PivotStates state) {
      this.pivotSub=pivotSub;
      this.state=state;
    
    addRequirements(pivotSub);
    }
    // Use addRequirements() here to declare subsystem dependencies.}}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pivotSub.setState(state);
  }}

